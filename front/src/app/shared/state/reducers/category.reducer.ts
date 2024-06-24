import { createReducer, on, createFeatureSelector, createSelector } from '@ngrx/store';
import * as CategoryActions from '../actions/category.action';
import { Category } from '../../models/category.model';

export interface CategoryState {
  categories: Category[];
  loading: boolean;
  error: any;
}

export const initialState: CategoryState = {
  categories: [],
  loading: false,
  error: null,
};

export const categoryReducer = createReducer(
  initialState,
  on(CategoryActions.loadCategories, state => ({ ...state, loading: true, error: null })),
  on(CategoryActions.loadCategoriesSuccess, (state, { categories }) => ({ ...state, categories, loading: false })),
  on(CategoryActions.loadCategoriesFailure, (state, { error }) => ({ ...state, loading: false, error }))
);

export const selectCategoryState = createFeatureSelector<CategoryState>('category');
export const selectCategories = createSelector(selectCategoryState, (state) => (state ? state.categories : []));
export const selectCategoryLoading = createSelector(selectCategoryState, (state) => (state ? state.loading : false));
export const selectCategoryError = createSelector(selectCategoryState, (state) => (state ? state.error : null));

