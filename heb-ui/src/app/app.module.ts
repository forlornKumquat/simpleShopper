import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { MainComponent } from './home/main/main.component';
import { HeaderComponent } from './shared/header/header.component';
import { FooterComponent } from './shared/footer/footer.component';
import { SearchBarComponent } from './page-components/search-bar/search-bar.component';

import { HttpClientModule } from '@angular/common/http';

import { ProductService } from './services/product.service';
import { AppRoutingModule } from './/app-routing.module';


@NgModule({
  declarations: [
    MainComponent,
    HeaderComponent,
    FooterComponent,
    SearchBarComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule
  ],
  providers: [ ProductService ],
  bootstrap: [MainComponent]
})
export class AppModule { }
