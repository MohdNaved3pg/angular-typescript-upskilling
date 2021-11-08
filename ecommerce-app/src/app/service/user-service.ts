import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { BehaviorSubject, Observable } from "rxjs";
import { map, mergeMap, tap } from "rxjs/operators";
import { environment } from "src/environments/environment";
import { Token } from "../model/token";
import { User } from "../model/user";


@Injectable({ providedIn: 'root' })
export class UserService {

    private endpointUrl = environment.backendURL + "/users";

    private currentUserSubject: BehaviorSubject<User>;
    public currentUserObservable : Observable<User>;
    public currentUser: User = new User();
    public token : string  = '';

    constructor(
        private httpClient : HttpClient
    ){
        let jsonStr : string = localStorage.getItem('currentUser') || '{"isEmpty":"true"}';
        console.log(jsonStr);
        this.currentUserSubject = new BehaviorSubject<User>(JSON.parse(jsonStr));
        this.currentUserObservable = this.currentUserSubject.asObservable();
        this.currentUserObservable.subscribe(user => this.currentUser = user);
    }

    register(user : User) : Observable<void> {
        return this.httpClient.post<void>(this.endpointUrl + "/register", user);
    }

    authenticate(username : String, password : String) : Observable<User> {
        return this.httpClient.post<Token>(this.endpointUrl + "/login", {username, password})
        .pipe(tap(token => localStorage.setItem("token", JSON.stringify(token.tokenStr))))
        .pipe(mergeMap(token => this.onLogin(token)))
        ;
    }

    onLogin(token : Token) : Observable<User> {
        this.token = token.tokenStr;
        return this.getUser(token.userId)
            .pipe(tap(user => {
                localStorage.setItem("currentUser", JSON.stringify(user));
                this.currentUserSubject.next(user);
            }));
    }

    getUser(userId : number) : Observable<User> {
        return this.httpClient.get<User>(this.endpointUrl);
    }

    logout() : void {
        localStorage.removeItem("currentUser");
        localStorage.removeItem("token");
        this.token = '';
        this.currentUserSubject.next(new User());
    }
}