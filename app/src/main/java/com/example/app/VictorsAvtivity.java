package com.example.app;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class VictorsAvtivity extends AppCompatActivity {

    TextView textView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_victors_story);
        textView = findViewById(R.id.textView20);

        String lore = "Hello wolrld!";
//        String lore = "Waggel komt van een boerderij in de polder van de Molenlanden. Hij was altijd nieuwsgierig naar de wereld buiten de boerderij. Toen hij oud genoeg was om de boerderij te verlaten, pakte hij meteen zijn spullen. Met een vat &quot;appelsap&quot; op zijn rug en zijn spullen aan zijn zijde, ging hij op avontuur. Nadat Waggel boer Hans vaarwel had gezegd, begon hij eindelijk aan zijn avontuur. Als eerste reisde hij door Nederland, waar hij molens en tulpen vond. Daarna ontdekte hij Duitsland, met zijn hoge bergen en oude kastelen die het land sierden. Vervolgens bezocht hij ook landen zoals Japan en China, waar de natuur steeds mooier werd en de cultuur steeds rijker. Hij reisde de hele wereld rond, bezocht Amerika en Canada, waar mensen dezelfde taal spreken maar toch uniek zijn. Waggel ontdekte al snel dat het leek alsof alle plekken op aarde al door mensen waren ontdekt. Het leek erop dat er niets meer was om te ontdekken. Op een dag, terwijl hij door de natuur wandelde, deed hij iets wat hij dacht dat onmogelijk was. Hij vond een plek die nog niet op zijn kaart stond: een betoverend woud waar magie overvloeide. Het bleek het wonderlijke woud te heten, waar dieren van over de hele wereld leefden. In dit woud maakte hij nieuwe vrienden waarmee hij dagenlang speelde. Na een aantal dagen met zijn nieuwe vriendjes gespeeld te hebben, besloot Waggel dat de hele wereld van deze magische plek moest leren. Hij besloot mensen naar het wonderlijke woud te leiden en ze voor te stellen aan zijn nieuwe vriendjes. Dus begon hij zijn reis opnieuw, dit keer om mensen kennis te laten maken met zijn vriendjes. Vandaag ben jij een van die mensen, dus kom mee met Waggel en ontdek het wonderlijke woud.";

        System.out.println("lore");

        textView.setText(lore);

        textView.setTextColor(0);
        
        textView.setMovementMethod(new ScrollingMovementMethod());
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
