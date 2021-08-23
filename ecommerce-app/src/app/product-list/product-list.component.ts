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

  constructor(private productService: ProductService, private route: ActivatedRoute, private router: Router) {
    this.router.routeReuseStrategy.shouldReuseRoute = () => false;
  }

  ngOnInit(): void {
    var categoryId = this.route.snapshot.paramMap.get('categoryId');
    if (categoryId != null) {
      this.productService.getProducts(categoryId as string).subscribe((data: Product[]) => {
        this.products = data
          , this.category = data[0].category
      });
    }
  }
  products: Product[] = []
  category: Category = { id: 0, name: "Invalid Category" };
  imageURLEndpoint: string = environment.backendURL + "/product/image/"
}
