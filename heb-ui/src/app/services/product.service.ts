import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

// use this model for response from server
import { ServerResponse } from './../shared/models/server-response.model';


@Injectable()
export class ProductService {

  filters;

  public displayResults: Array<ServerResponse>;

  constructor(private http: HttpClient) { }

  request = 'http://localhost:8080/';

  getProducts() {
    if (this.filters != null) {
      this.filters = this.filters.replace(/[^a-zA-Z0-9 ]/g, '');
    }
    if (this.filters != null) {
      return this.http.get<any>(this.request + `products?search=${this.filters}` );
    }
    return this.http.get<any>(this.request + 'products');
  }

  filterProducts(pr: boolean, gr: boolean, ph: boolean) {
    return this.http.get<any>(this.request + `filter?pr=${pr}&gr=${gr}&ph=${ph}`);
  }
}
