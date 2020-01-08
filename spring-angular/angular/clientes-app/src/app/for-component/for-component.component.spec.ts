import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ForComponentComponent } from './for-component.component';

describe('ForComponentComponent', () => {
  let component: ForComponentComponent;
  let fixture: ComponentFixture<ForComponentComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ForComponentComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ForComponentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
