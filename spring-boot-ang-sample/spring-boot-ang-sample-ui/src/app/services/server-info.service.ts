import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/internal/Observable';
import { ServerInfo } from '../model/server-info';

@Injectable({
  providedIn: 'root'
})
export class ServerInfoService {

  constructor(private http: HttpClient) { }

  url1 = 'api/time/local';

  getServerInfo(): Observable<ServerInfo> {
    return this.http.get<ServerInfo>(this.url1);
  }  

}
