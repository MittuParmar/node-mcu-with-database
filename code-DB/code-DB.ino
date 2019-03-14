#include <ESP8266WiFi.h>// Librarie
const char* ssid     = "Mittu";// SSID
const char* password = "qwertyuiop";//PASSWORD
const char* host = "192.168.137.1";// Host
static int temp;
static String s;
void setup() {
  pinMode(2,OUTPUT);
  pinMode(5,INPUT);
  // Serial
  Serial.begin(115200);
  delay(10);

  // We start by connecting to a WiFi network
  Serial.println();
  Serial.println();
  Serial.print("Connecting to ");
  Serial.println(ssid);
  WiFi.begin(ssid, password);
  
  while (WiFi.status() != WL_CONNECTED) {
    delay(500);
    Serial.print(".");
  }
  Serial.println("");
  Serial.println("WiFi connected");  
}
void loop() {
  temp=digitalRead(5);
  if(temp==LOW)
  {     
      if(s=="LED_OFF")
      {
        s="LED_ON";
        digitalWrite(2,LOW);
        Serial.print("highhhhhhhhhhhhhhhhhhhhhhh"); 
      }
      else
      {
        s="LED_OFF";
        digitalWrite(2,HIGH);
        Serial.print("lowwwwwwwwwwwwwwwwwwwwwwww");
      }
      Serial.print("connecting to ");
      Serial.println(host);  
      WiFiClient client;  // Use WiFiClient class to create TCP connections
      const int httpPort = 8080;
      if (!client.connect(host, httpPort)) {
        Serial.println("connection failed");
        return;
      }
      String url = "/IoT_Databases/test?name="+s;    // We now create a URI for the request
      Serial.print("Requesting URL: ");
      Serial.println(url);
      
      client.print(String("GET ") + url);    // Send request
      unsigned long timeout = millis();
      while (client.available() == 0) {
        if (millis() - timeout > 5000) {
          Serial.println(">>> Client Timeout !");
          client.stop();
          return;
        }
      }  // Read all the lines from the answer
      while(client.available()){
        String line = client.readStringUntil('\r');
        Serial.print(line);
      }
    
      // Close connecting
      Serial.println();
      Serial.println("closing connection");
      }
}
