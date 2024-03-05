import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent implements OnInit{
  
  constructor(private router: Router) { }

  ngOnInit(): void {}

  logout() {
    // Perform logout action
    // For example, clear user session or token
    // Then navigate to the sign-in page
    this.router.navigate(['/signin']);
  }


}
