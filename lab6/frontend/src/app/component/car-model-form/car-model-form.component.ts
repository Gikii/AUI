import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ApiService } from '../../service/api.service';
import { CarModel } from '../../model/models';

@Component({
  selector: 'app-car-model-form',
  templateUrl: './car-model-form.html',
  styleUrls: ['./car-model-form.css'],
  standalone: false
})
export class CarModelFormComponent implements OnInit {
  
  model: CarModel = { modelName: '', productionYear: 2024 };
  brandId: string = '';
  isEditMode = false;

  constructor(
    private api: ApiService,
    private route: ActivatedRoute,
    private router: Router,
    private cdr: ChangeDetectorRef 
  ) {}

  ngOnInit(): void {
    this.brandId = this.route.snapshot.params['brandId'];
        
    const modelId = this.route.snapshot.params['modelId'];

    if (modelId) {
      this.isEditMode = true;
          
      this.api.getModel(modelId).subscribe({
        next: (data) => {
          console.log('Dane modelu do edycji:', data);
          this.model = data;
          this.cdr.detectChanges(); 
        },
        error: (err) => console.error(err)
      });
    }
  }

  onSubmit() {    
    this.model.brand = { 
      id: this.brandId,
      brandId: this.brandId, 
      name: '', 
      country: '' 
    };

    if (this.isEditMode) {      
      this.api.updateModel(this.model.id!, this.model).subscribe(() => {      
        this.router.navigate(['/brands', this.brandId]);
      });
    } else {      
      this.api.createModel(this.model).subscribe(() => {
        this.router.navigate(['/brands', this.brandId]);
      });
    }
  }
}