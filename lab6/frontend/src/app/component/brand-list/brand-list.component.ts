import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { ApiService } from '../../service/api.service';
import { CarBrand } from '../../model/models';

@Component({
  selector: 'app-brand-list',
  standalone: false, 
  templateUrl: './brand-list.html', 
  styleUrls: ['./brand-list.css']
})
export class BrandListComponent implements OnInit {
  brands: CarBrand[] = [];

  constructor(
    private api: ApiService,
    private cdr: ChangeDetectorRef 
  ) { }

  ngOnInit(): void {
    this.fetchBrands();
  }

  fetchBrands() {
    this.api.getBrands().subscribe({
      next: (data) => {
        this.brands = data;
        this.cdr.detectChanges(); 
      },
      error: (err) => console.error(err)
    });
  }

  deleteBrand(id: string) {
    this.api.deleteBrand(id).subscribe(() => this.fetchBrands());
  }
}