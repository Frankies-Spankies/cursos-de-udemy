import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class PublicService {
  constructor(private http:HttpClient) { }
  private url: string = 'http://localhost:8080/api/';


  inicio():Observable<any>{
    return this.http.get<any>(this.url+'inicio');
  }



}
