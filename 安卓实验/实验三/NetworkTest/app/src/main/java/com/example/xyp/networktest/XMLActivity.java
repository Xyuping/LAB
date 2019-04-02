package com.example.xyp.networktest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.StringReader;
import java.text.ParseException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class XMLActivity extends AppCompatActivity {
    TextView xmlText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xml);

        xmlText=(TextView)findViewById(R.id.xmlTextView);
        Button pbutton = (Button)findViewById(R.id.pullXML);
        pbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                xmlText.setText("");
                sendRequestWithOkHttpPull();
            }
        });

        Button sbutton = (Button)findViewById(R.id.saxXML);
        sbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                xmlText.setText("");
                sendRequestWithOkHttpSAX();
            }
        });

    }

    void sendRequestWithOkHttpPull(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder().url("http://172.21.47.248/get_data.xml").build();
                try{
                    Response response = client.newCall(request).execute();
                    String responseData = response.body().string();
                    parseXMLWithPull(responseData);
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }).start();
    }
    void  parseXMLWithPull(String xmlData){
        try{
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser xmlPullParser = factory.newPullParser();
            xmlPullParser.setInput(new StringReader(xmlData));
            int eventType = xmlPullParser.getEventType();
            String id ="";
            String name = "";
            String version = "";
            while(eventType!=XmlPullParser.END_DOCUMENT){
                String nodeName = xmlPullParser.getName();
                switch (eventType){
                    case XmlPullParser.START_TAG:{
                        if("id".equals(nodeName)){
                            id=xmlPullParser.nextText();
                        }
                        else if("name".equals(nodeName)){
                            name=xmlPullParser.nextText();
                        }
                        else if("version".equals(nodeName)){
                            version=xmlPullParser.nextText();
                        }
                    }
                    break;
                    case XmlPullParser.END_TAG:{
                        if("app".equals(nodeName)){
                            showXMLdata("id:"+id+" name:"+name+" version:"+version);
                        }
                    }
                    break;
                    default:break;
                }
                eventType=xmlPullParser.next();
            }
        }catch (XmlPullParserException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
    }


    void sendRequestWithOkHttpSAX(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient client= new OkHttpClient();
                Request request = new Request.Builder().url("http://172.21.47.248/get_data.xml").build();
                try{
                    Response response = client.newCall(request).execute();
                    String responseData = response.body().string();
                    parseXMLWithSAX(responseData);
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }).start();
    }

    void parseXMLWithSAX(String xmlData){
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try{
            XMLReader xmlReader = factory.newSAXParser().getXMLReader();
            ContentHandler handler = new  ContentHandler();
            xmlReader.setContentHandler(handler);
            xmlReader.parse(new InputSource(new StringReader(xmlData)));
        }catch (SAXException e){
            e.printStackTrace();
        }catch (ParserConfigurationException e ){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    class ContentHandler extends DefaultHandler{
        String nodeName;
        StringBuilder id;
        StringBuilder name;
        StringBuilder version;

        @Override
        public void startDocument() throws SAXException {
            id = new StringBuilder();
            name = new StringBuilder();
            version = new StringBuilder();
        }

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            nodeName = localName;
        }

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            if("id".equals(nodeName))
                id.append(ch,start,length);
            else if("name".equals(nodeName))
                name.append(ch,start,length);
            else if("version".equals(nodeName))
                version.append(ch,start,length);
        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            if("app".equals(localName)){
                showXMLdata("id:"+id.toString()+" name:"+name.toString()+" version:"+version.toString());
                id.setLength(0);
                name.setLength(0);
                version.setLength(0);
            }
        }

        @Override
        public void endDocument() throws SAXException {
            super.endDocument();
        }
    }

    void showXMLdata(final String response){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                String s=xmlText.getText().toString()+response;
                xmlText.setText(s);
            }
        });
    }
}
