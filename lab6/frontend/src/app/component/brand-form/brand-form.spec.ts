import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BrandForm } from './brand-form.component';

describe('BrandForm', () => {
  let component: BrandForm;
  let fixture: ComponentFixture<BrandForm>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [BrandForm]
    })
    .compileComponents();

    fixture = TestBed.createComponent(BrandForm);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
