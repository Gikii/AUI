import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ApiService } from '../../service/api.service';
import { CarBrand, CarModel } from '../../model/models';

@Component({
  selector: 'app-brand-details',
  templateUrl: './brand-details.html', 
  styleUrls: ['./brand-details.css'],
  standalone: false
})
export class BrandDetailsComponent implements OnInit {
  brand: CarBrand | undefined;
  models: CarModel[] = [];

  constructor(
    private api: ApiService,
    private route: ActivatedRoute,
    private cdr: ChangeDetectorRef 
  ) {}

  ngOnInit(): void {
    const brandId = this.route.snapshot.params['id'];
    this.loadData(brandId);
  }

  loadData(brandId: string) {
    // 1. Pobierz dane o Marce
    this.api.getBrand(brandId).subscribe(data => {
      this.brand = data;
      this.cdr.detectChanges();
    });

    
    this.api.getModelsByBrand(brandId).subscribe({
      next: (data) => {
        console.log('Modele przyszły z serwera:', data); 
        this.models = data;
        this.cdr.detectChanges(); 
      },
      error: (err) => console.error('Błąd pobierania modeli:', err)
    });
  }

  deleteModel(modelId: string) {
    if(confirm('Czy na pewno chcesz usunąć ten model?')) {
      this.api.deleteModel(modelId).subscribe(() => {
        if (this.brand?.id) {
          this.loadData(this.brand.id);
        }
      });
    }
  }
}