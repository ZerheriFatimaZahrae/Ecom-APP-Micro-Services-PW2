import {Component, OnInit} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-bills',
  templateUrl: './bills.component.html',
  styleUrl: './bills.component.css'
})
export class BillsComponent implements OnInit {
  bills :any;
  id!:number;
  totalAmount: number = 0;
  constructor(private http:HttpClient, private router: Router, private route:ActivatedRoute) {
    this.id=route.snapshot.params['id'];
  }

  ngOnInit(): void {
    this.http.get("http://localhost:9999/BILLING-SERVICE/fullBill/"+this.id)
      .subscribe({
        next : (data)=>{
          this.bills=data;
          this.calculateTotalAmount(); // Calculer le total après avoir reçu les données
        },
        error : (err)=>{}
      });
  }

  getProductDetails(o: any) {

  }
  calculateTotalAmount() {
    // Utilise `reduce` pour parcourir chaque élément dans `productItemList` et calculer le montant total.
    this.totalAmount = this.bills.productItemList.reduce((acc: number, item: any) => {
      // Pour chaque produit, calcule le montant après application de la réduction.
      const amount = item.quantity * item.price * (1 - item.discount);
      return acc + amount; // Ajoute le montant au total.
    }, 0); // Initialisation de l'accumulateur à 0.
  }

}
