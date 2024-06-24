import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { Layout1RoutingModule } from './layout1-routing.module';
import { Layout1Component } from './layout1/layout1.component';
import { HeaderModule } from '../core/header/header.module';
import { FooterModule } from '../core/footer/footer.module';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ButtonModule } from 'primeng/button';
import { RippleModule } from 'primeng/ripple';


@NgModule({
  declarations: [
    Layout1Component
  ],
  imports: [
    CommonModule,
    Layout1RoutingModule,
    FooterModule,
    HeaderModule,

    ButtonModule,

    ]
})
export class Layout1Module { }
