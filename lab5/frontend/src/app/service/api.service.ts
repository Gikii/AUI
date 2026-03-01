import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { CarBrand, CarModel } from '../model/models';

@Injectable({
  providedIn: 'root'
})
export class ApiService {
  private readonly BASE_URL = 'http://localhost:8080/api';

  constructor(private http: HttpClient) { }

  // Marki
  getBrands(): Observable<CarBrand[]> { return this.http.get<CarBrand[]>(`${this.BASE_URL}/brands`); }
  getBrand(id: string): Observable<CarBrand> { return this.http.get<CarBrand>(`${this.BASE_URL}/brands/${id}`); }
  createBrand(brand: CarBrand): Observable<CarBrand> { return this.http.post<CarBrand>(`${this.BASE_URL}/brands`, brand); }
  updateBrand(id: string, brand: CarBrand): Observable<CarBrand> { return this.http.put<CarBrand>(`${this.BASE_URL}/brands/${id}`, brand); }
  deleteBrand(id: string): Observable<void> { return this.http.delete<void>(`${this.BASE_URL}/brands/${id}`); }

  // Modele
  getModelsByBrand(brandId: string): Observable<CarModel[]> { return this.http.get<CarModel[]>(`${this.BASE_URL}/models/brand/${brandId}`); }
  getModel(id: string): Observable<CarModel> { return this.http.get<CarModel>(`${this.BASE_URL}/models/${id}`); }
  createModel(model: CarModel): Observable<CarModel> { return this.http.post<CarModel>(`${this.BASE_URL}/models`, model); }
  updateModel(id: string, model: CarModel): Observable<CarModel> { return this.http.put<CarModel>(`${this.BASE_URL}/models/${id}`, model); }
  deleteModel(id: string): Observable<void> { return this.http.delete<void>(`${this.BASE_URL}/models/${id}`); }
}