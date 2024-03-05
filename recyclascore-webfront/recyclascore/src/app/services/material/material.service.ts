import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { MaterialObject } from 'src/app/interfaces/material/material-object';

  
@Injectable({
  providedIn: 'root'
})

export class MaterialService {
  private apiUrl = 'http://localhost:8080';

  constructor(private http : HttpClient) {}

  getMaterialList() : Observable<MaterialObject[]> {
    return this.http.get<MaterialObject[]>(`${this.apiUrl}/material/all`);
  }

  getMaterial(id : number) : Observable<MaterialObject> {
    return this.http.get<MaterialObject>(`${this.apiUrl}/material/${id}`);
  }
}
  