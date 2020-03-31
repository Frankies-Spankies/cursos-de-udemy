import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AdminService {
  constructor(private http:HttpClient) { }
  private url: string = 'http://localhost:8080/api/';


  ainicio():Observable<any>{
    return this.http.get<any>(this.url+'ainicio');
  }
}
