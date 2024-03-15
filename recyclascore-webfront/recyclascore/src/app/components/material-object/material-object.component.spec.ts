import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MaterialObjectComponent } from './material-object.component';

describe('MaterialObjectComponent', () => {
  let component: MaterialObjectComponent;
  let fixture: ComponentFixture<MaterialObjectComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [MaterialObjectComponent]
    });
    fixture = TestBed.createComponent(MaterialObjectComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
