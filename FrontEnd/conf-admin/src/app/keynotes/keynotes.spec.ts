import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Keynotes } from './keynotes';

describe('Keynotes', () => {
  let component: Keynotes;
  let fixture: ComponentFixture<Keynotes>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [Keynotes]
    })
    .compileComponents();

    fixture = TestBed.createComponent(Keynotes);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
