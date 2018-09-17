import { Component, OnInit, DoCheck } from '@angular/core';
import { ProductService } from './../../services/product.service';

@Component({
  selector: 'app-root',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.css', '../../styles/crow.css', '../../styles/main.css'],
  providers: [ ProductService ]
})
export class MainComponent implements OnInit, DoCheck {

  public displayResults;

  constructor(private productService: ProductService) { }

  ngOnInit() {
    this.displayResults = this.productService.displayResults;
  }

  ngDoCheck() {
    if ( this.displayResults !== this.productService.displayResults ) {
      this.setMainList();
    }
  }

  setMainList() {
    this.displayResults = this.productService.displayResults;
  }

}
