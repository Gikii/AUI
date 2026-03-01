export interface CarBrand {
  id?: string;
  brandId?: string; 
  name: string;
  country: string;
}

export interface CarModel {
  id?: string;
  modelName: string;
  productionYear: number;
  brand?: CarBrand;
}