import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ApiService } from '../../service/api.service';
import { CarBrand } from '../../model/models';

@Component({
  selector: 'app-brand-form',
  templateUrl: './brand-form.html',
  styleUrls: ['./brand-form.css'],
  standalone: false
})
export class BrandFormComponent implements OnInit {
  
  brand: CarBrand = { name: '', country: '' };
  isEditMode = false;

  constructor(
    private api: ApiService,
    private route: ActivatedRoute,
    private router: Router,
    private cdr: ChangeDetectorRef 
  ) {}

  ngOnInit(): void {
    const id = this.route.snapshot.params['id'];

    if (id) {
      this.isEditMode = true;
      this.api.getBrand(id).subscribe({
        next: (data) => {
          console.log('Dane do edycji przyszły:', data);
          this.brand = data;                    
          this.cdr.detectChanges();
        },
        error: (err) => console.error('Błąd pobierania:', err)
      });
    }
  }

  onSubmit() {
    if (this.isEditMode) {
      this.api.updateBrand(this.brand.id!, this.brand).subscribe(() => {
        this.router.navigate(['/brands']);
      });
    } else {      
      this.api.createBrand(this.brand).subscribe(() => {
        this.router.navigate(['/brands']);
      });
    }
  }
}