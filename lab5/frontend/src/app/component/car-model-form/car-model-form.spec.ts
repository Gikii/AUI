import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CarModelForm } from './car-model-form.component';

describe('CarModelForm', () => {
  let component: CarModelForm;
  let fixture: ComponentFixture<CarModelForm>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [CarModelForm]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CarModelForm);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
