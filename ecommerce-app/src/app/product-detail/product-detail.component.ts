import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { environment } from 'src/environments/environment';
import { Product } from '../model/product';
import { ProductService } from '../service/product-service';

@Component({
  selector: 'app-product-detail',
  templateUrl: './product-detail.component.html',
  styleUrls: ['./product-detail.component.css'],
  providers: [ProductService]
})
export class ProductDetailComponent implements OnInit {

  constructor(private productService: ProductService, private route: ActivatedRoute) { }
  product: any = {}
  imageURLEndpoint: string = environment.backendURL + "/product/image/"
  ngOnInit(): void {
    var productId = this.route.snapshot.paramMap.get('productId');
    if (productId != null) {
      this.productService.getProduct(productId as string).subscribe((data: Product) =>
        this.product = data);
    }
  }

}
