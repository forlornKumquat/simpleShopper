import { Component, OnInit, Input } from '@angular/core';
import { callLifecycleHooksChildrenFirst } from '@angular/core/src/view/provider';

import { ProductService } from './../../services/product.service';

@Component({
  selector: 'app-search-bar',
  templateUrl: './search-bar.component.html',
  styleUrls: ['./search-bar.component.css', '../../styles/crow.css', '../../styles/main.css']
})
export class SearchBarComponent implements OnInit {

  @Input()
  displayResults;

  selectedPharmacy = false;
  selectedProduce = false;
  selectedGrocery = false;


  constructor(private productService: ProductService) {}

  ngOnInit() {
    this.setSearch(null);
  }

  setSearch(value) {
    this.toggleAllOff();
    this.productService.filters = value;
    this.productService.getProducts().subscribe(
      data => {
        this.displayResults = this.sercheableize(data);
        this.productService.displayResults = this.displayResults;
      },
      error => console.error('Error with data.')
    );
  }

  togglePharmacy() {
    this.toggleAllOff();
    this.selectedPharmacy = true;
    this.runService(false, false, true);
  }
  toggleProduce() {
    this.toggleAllOff();
    this.selectedProduce = true;
    this.runService(true, false, false);
  }
  toggleGrocery() {
    this.toggleAllOff();
    this.selectedGrocery = true;
    this.runService(false, true, false);
  }
  toggleHome() {
    this.toggleAllOff();
    this.setSearch(null);
  }

  toggleAllOff() {
    this.productService.filters = null;
    this.selectedPharmacy = false;
    this.selectedProduce = false;
    this.selectedGrocery = false;
  }

  sercheableize(inputData: any): void {
    for (const result of inputData) {
      result.relevant = result.id
        + ' ' + result.description
        + ' ' + result.price
        + ' ' + result.department
        + ' ' + result.unit
        + ' ' + result.shelfLife;
      result.visible = true;
    }
    return inputData;
  }

  runService(one: boolean, two: boolean, three: boolean) {
    this.productService.filterProducts(one, two, three).subscribe(
      data => {
        this.displayResults = this.sercheableize(data);
        this.productService.displayResults = this.displayResults;
      },
      error => console.error('Error with data.')
    );
  }
}
