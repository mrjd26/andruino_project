char val;
const int LIGHT=8;    //light on Digital Pin 9


void setup() {
  Serial.begin(9600);
  pinMode(LIGHT, OUTPUT);
}

void loop(){
  if(Serial.available()>0){
  val = Serial.read();
  if(val=='y'){
  Serial.print("The Light TURNS ON");
  digitalWrite(LIGHT,HIGH);
  }
  if(val=='n'){
    Serial.print("the light turns OFF");
    digitalWrite(LIGHT,LOW);
  }
  
  }
}
