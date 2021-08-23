import { Component, OnInit } from '@angular/core';
import { Category } from '../model/category';
import { CategoryService } from '../service/category-service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css'],
  providers: [CategoryService]
})
export class HeaderComponent implements OnInit {

  categories: Category[] = [];
  constructor(private categoryService: CategoryService) { }
  ngOnInit(): void {
    this.categoryService.getCategories().subscribe((data: Category[]) => this.categories = data);
  }
}
