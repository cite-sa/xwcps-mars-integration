import { NgModule } from "@angular/core";
import { MatToolbarModule, MatTableModule, MatExpansionModule, MatProgressSpinnerModule, MatGridListModule, MatCardModule, MatButtonModule, MatIconModule } from "@angular/material";

@NgModule({
	imports: [
		MatToolbarModule,
		MatTableModule,
		MatExpansionModule,
		MatProgressSpinnerModule,
		MatGridListModule,
		MatCardModule,
		MatButtonModule,
		MatIconModule
	],
	exports: [
		MatToolbarModule,
		MatTableModule,
		MatExpansionModule,
		MatProgressSpinnerModule,
		MatGridListModule,
		MatCardModule,
		MatButtonModule,
		MatIconModule
	]
})

export class AppMaterialModule {}
