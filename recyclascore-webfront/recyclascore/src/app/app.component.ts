import { Component, OnInit } from '@angular/core';
import { MaterialService } from './services/material.service';
import { MaterialObject } from './interfaces/material/material-object';



@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss'],
})
export class AppComponent implements OnInit {
  title = 'recyclastore';
  materialObjectList: MaterialObject[] = [];
  selectedMaterial: MaterialObject | undefined;
 
  constructor(private materialService : MaterialService) {}

  ngOnInit() : void {
    this.getMaterialList();
   // this.getMaterial(this.selectedMaterial)
  }

 public getMaterialList() : void {
  this.materialService.getMaterialList().subscribe(
    materialListData => {
      this.materialObjectList = materialListData;
    },
    error => {
      console.error('Error fetching material list:', error);
    }
  );
  }

  public getMaterial(selectedMaterial : MaterialObject) {
    this.materialService.getMaterial(selectedMaterial.id).subscribe((material : MaterialObject) => {
      this.selectedMaterial = material
    }, error => {
      console.error('Error fetching material on getMaterial():', error);
    })
  }
  

  onMaterialSelected(event: any) {
    const selectedMaterialId = event.target.value;
   // Find the selected material from the list
   this.selectedMaterial = this.materialObjectList.find(material => material.id === parseInt(selectedMaterialId));
   if (this.selectedMaterial?.recyclable === true) {
     'vrai'
   } else {
     'false'
   }
  }

}
