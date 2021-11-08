import { Component, OnInit } from '@angular/core';
import { Category } from '../model/category';
import { User } from '../model/user';
import { CategoryService } from '../service/category-service';
import { UserService } from '../service/user-service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css'],
  providers: [CategoryService]
})
export class HeaderComponent implements OnInit {

  categories: Category[] = [];
  currentUser: User;
  constructor(
    private userService : UserService,
    private categoryService: CategoryService) {
      this.currentUser = new User();
     }
  ngOnInit(): void {
    this.categoryService.getCategories().subscribe((data: Category[]) => this.categories = data);
    this.userService.currentUserObservable.subscribe(user => this.currentUser = user);
  }

  onLogout() : void {
    this.userService.logout();
  }
}
