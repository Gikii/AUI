import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CarModelDetails } from './car-model-details.component';

describe('CarModelDetails', () => {
  let component: CarModelDetails;
  let fixture: ComponentFixture<CarModelDetails>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [CarModelDetails]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CarModelDetails);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
