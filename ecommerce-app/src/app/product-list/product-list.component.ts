import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { environment } from 'src/environments/environment';
import { Category } from '../model/category';
import { Product } from '../model/product';
import { CategoryService } from '../service/category-service';
import { ProductService } from '../service/product-service';

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.css'],
  providers: [ProductService]
})
export class ProductListComponent implements OnInit {

  products: Product[] = []
  filteredProductList: Product[] = []
  category: Category = { id: 0, name: "" };
  imageURLEndpoint: string = environment.backendURL + "/product/image/"
  sortProductBy: string = 'name-asc';

  constructor(private productService: ProductService, private route: ActivatedRoute, private router: Router) {
    this.router.routeReuseStrategy.shouldReuseRoute = () => false;
  }

  ngOnInit(): void {
    var categoryId = this.route.snapshot.paramMap.get('categoryId');
    this.productService.getProducts(categoryId as string)
      .subscribe((data: Product[]) => this.onProductsRecieved(data, categoryId as string));
  }

  onProductsRecieved(data : Product[], categoryId : string) : void {
    this.products = data;
    if(categoryId != null) {
      this.category = data[0].category;
    }
    this.sortProductList(),
    this.products.forEach(this.setRandomCardColor),
    this.filteredProductList = this.products
  }

  sortProductList(): void {
    var propertyVsDirection = this.sortProductBy.split("-");
    var property = propertyVsDirection[0] as 'name' | 'price';
    this.filteredProductList = this.filteredProductList.sort((a, b) => {
      if (a[property] > b[property]) {
        return (propertyVsDirection[1] === 'desc') ? -1 : 1;
      }
      if (a[property] < b[property]) {
        return (propertyVsDirection[1] === 'desc') ? 1 : -1;
      }
      return 0;
    })
  }

  setRandomCardColor(product: Product) {
    var color = '#';
    for (var i = 0; i < 6; i++) {
      color += Math.floor(Math.random() * 10);
    }
    product.cardColor = color;
  }

  filterProducts(inputText: any) {
    if (!inputText) {
      this.filteredProductList = this.products;
    } else {
      this.filteredProductList = this.products.filter(
        item => item.name.toLowerCase().indexOf(inputText.toLowerCase()) > -1
      );
    }
    this.sortProductList();
  }
}
