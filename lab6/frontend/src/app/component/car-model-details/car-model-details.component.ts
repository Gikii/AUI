import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ApiService } from '../../service/api.service';
import { CarModel } from '../../model/models';

@Component({
  selector: 'app-car-model-details',
  templateUrl: './car-model-details.html',
  styleUrls: ['./car-model-details.css'],
  standalone: false 
})
export class CarModelDetailsComponent implements OnInit {
  model: CarModel | undefined;
  brandId: string = '';

  constructor(
    private api: ApiService,
    private route: ActivatedRoute,
    private cdr: ChangeDetectorRef 
  ) {}

  ngOnInit(): void {
    this.brandId = this.route.snapshot.params['brandId'];
    const modelId = this.route.snapshot.params['modelId'];

    this.api.getModel(modelId).subscribe({
      next: (data) => {
        console.log('Szczegóły modelu:', data);
        this.model = data;
        this.cdr.detectChanges(); 
      },
      error: (err) => console.error('Błąd:', err)
    });
  }
}