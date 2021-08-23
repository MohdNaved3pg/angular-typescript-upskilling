import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Category } from '../model/category';
import { environment } from 'src/environments/environment';
@Injectable()
export class CategoryService {
    private endpointUrl = environment.backendURL;
    constructor(private http: HttpClient) { }
    getCategories(): Observable<Category[]> {
        return this.http.get<Category[]>(this.endpointUrl + "/categories");
    }
    getCategory(id: number): Observable<Category> {
        return this.http.get<Category>(this.endpointUrl + "/category/" + id);
    }

}
