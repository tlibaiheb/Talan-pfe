import { Actions, createEffect, ofType } from '@ngrx/effects';
import { of } from 'rxjs';
import { catchError, map, mergeMap, tap } from 'rxjs/operators';
import * as CategoryActions from '../actions/category.action';
import { CategoryService } from '../../services/nomenclature.service';
import { Injectable } from '@angular/core';

@Injectable()
export class CategoryEffects {
  loadCategories$ = createEffect(() =>
    this.actions$.pipe(
      ofType(CategoryActions.loadCategories),
      mergeMap(() =>
        this.categoryService.getCategories().pipe(
          map(categories => {
            sessionStorage.setItem('categories', JSON.stringify(categories));
            return CategoryActions.loadCategoriesSuccess({ categories });
          }),
          catchError(error => of(CategoryActions.loadCategoriesFailure({ error })))
        )
      )
    )
  );

  constructor(private actions$: Actions, private categoryService: CategoryService) {}
}
