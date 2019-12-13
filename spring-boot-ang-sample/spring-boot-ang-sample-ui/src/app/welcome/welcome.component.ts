import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs/internal/Observable';
import { ServerInfo } from '../model/server-info';
import { ServerInfoService } from '../services/server-info.service';

@Component({
  selector: 'app-welcome',
  templateUrl: './welcome.component.html',
  styleUrls: ['./welcome.component.css']
})
export class WelcomeComponent implements OnInit {

  serverInfo = new ServerInfo();
  constructor(private http: HttpClient, public serverInfoService: ServerInfoService) {}

  ngOnInit() {
  }

  getInfoFromServer() {
    this.serverInfoService.getServerInfo().subscribe(si => this.serverInfo = si);
  }
}
