import 'dart:math';

import 'package:flutter/material.dart';

void main() {
  runApp(MyApp());
}
String emptyBox = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR13n9dse9BPQiR-P9V4UFbC0GyELXMzHK08tqAJukSbyKh5KLdj6-5RLR48KLVecJaVtc&usqp=CAU";
String closedBox = "https://thumbs.dreamstime.com/b/%D0%B7%D0%B0%D0%BA%D1%80%D1%8B%D1%82%D0%B0%D1%8F-%D0%BA%D0%B0%D1%80%D1%82%D0%BE%D0%BD%D0%BD%D0%B0%D1%8F-%D0%BA%D0%BE%D1%80%D0%BE%D0%B1%D0%BA%D0%B0-%D1%81%D0%B2%D1%8F%D0%B7%D0%B0%D0%BD%D0%BD%D0%B0%D1%8F-%D1%82%D0%B5%D1%81%D1%8C%D0%BC%D0%BE%D0%B9-%D0%B2%D0%B2%D0%B5%D1%80%D1%85-36222452.jpg";
String prizeBox = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQAh0W-lrzRWU3tboopIxyiRgaXSaXlFzuiGg&usqp=CAU";
String leftBox = closedBox;
String rightBox = closedBox;
String buttonText = "";
String buttonTextC = "Продолжить";

class MyApp extends StatelessWidget {


  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Flutter Demo',
      theme: ThemeData(
        // This is the theme of your application.
        //
        // Try running your application with "flutter run". You'll see the
        // application has a blue toolbar. Then, without quitting the app, try
        // changing the primarySwatch below to Colors.green and then invoke
        // "hot reload" (press "r" in the console where you ran "flutter run",
        // or simply save your changes to "hot reload" in a Flutter IDE).
        // Notice that the counter didn't reset back to zero; the application
        // is not restarted.
        primarySwatch: Colors.blue,
      ),
      home: MyHomePage(title: 'Random'),
    );
  }
}

class MyHomePage extends StatefulWidget {
  MyHomePage({Key? key, required this.title}) : super(key: key);

  // This widget is the home page of your application. It is stateful, meaning
  // that it has a State object (defined below) that contains fields that affect
  // how it looks.

  // This class is the configuration for the state. It holds the values (in this
  // case the title) provided by the parent (in this case the App widget) and
  // used by the build method of the State. Fields in a Widget subclass are
  // always marked "final".

  final String title;

  @override
  _MyHomePageState createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {
  int _counterVin = 0;
  int _counterLose = 0;
  int choseWidth = 200;
  int choseHeidht = 200;
  int standartWidth = 120;
  int standartHeidht = 120;
  int currentWidthLeft = 120;
  int currentHeidhtLeft = 120;
  int currentWidthRight = 120;
  int currentHeidhtRight= 120;


  String message = "ПОБЕДА";

  void  ContinueGame(){

    setState(() {
      buttonText = "";
      leftBox = closedBox;
      rightBox = closedBox;
      currentWidthLeft = standartHeidht;
      currentHeidhtLeft = standartHeidht;
      currentWidthRight = standartWidth ;
      currentHeidhtRight= standartHeidht ;

    });

  }
  void generatePrize(int boxNumber){
    setState(() {
    Random rand = new Random();
    buttonText = "продолжить";

// nextInt as provided by Random is exclusive of the top value so you need to add 1
    if (boxNumber == 1)
      {
         setState(() {
            currentWidthLeft = standartWidth;
            currentHeidhtLeft = standartHeidht;
            currentWidthRight = choseWidth;
            currentHeidhtRight= choseHeidht;

         });
      }
    else
      {
        setState(() {
          currentWidthLeft = choseWidth;
          currentHeidhtLeft = choseHeidht;
          currentWidthRight = standartWidth ;
          currentHeidhtRight= standartHeidht ;

        });
      }
    int randomNum = rand.nextInt((1 - 0) + 1);
    print(randomNum);
    if (randomNum == 1)
    {
      leftBox = emptyBox;
      rightBox = prizeBox;
      if (randomNum == boxNumber)
        {
          _incrementCounterVin();
          setState(() {
            message = "ПОБЕДА";
          });
        }
      else
        {
          _incrementCounterLose();
          setState(() {
            message = "ПОРАЖЕНИЕ";
          });
        }
    }
    else
    {
      leftBox =prizeBox ;
      rightBox = emptyBox;
      if (randomNum == boxNumber)
      {
        _incrementCounterVin();
        setState(() {
          message = "ПОБЕДА";
        });
      }
      else
      {
        _incrementCounterLose();
        setState(() {
          message = "ПОРАЖЕНИЕ";
        });
      }
    }
    });
  }
  void _incrementCounterVin() {
    setState(() {
      // This call to setState tells the Flutter framework that something has
      // changed in this State, which causes it to rerun the build method below
      // so that the display can reflect the updated values. If we changed
      // _counter without calling setState(), then the build method would not be
      // called again, and so nothing would appear to happen.
      _counterVin++;
    });
  }

  void _incrementCounterLose() {
    setState(() {
      // This call to setState tells the Flutter framework that something has
      // changed in this State, which causes it to rerun the build method below
      // so that the display can reflect the updated values. If we changed
      // _counter without calling setState(), then the build method would not be
      // called again, and so nothing would appear to happen.
      _counterLose++;
    });
  }

  @override
  Widget build(BuildContext context) {
    // This method is rerun every time setState is called, for instance as done
    // by the _incrementCounter method above.
    //
    // The Flutter framework has been optimized to make rerunning build methods
    // fast, so that you can just rebuild anything that needs updating rather
    // than having to individually change instances of widgets.
    return Scaffold(
      appBar: AppBar(
        // Here we take the value from the MyHomePage object that was created by
        // the App.build method, and use it to set our appbar title.
        title: Text(widget.title),
      ),

      body: Center(
        // Center is a layout widget. It takes a single child and positions it
        // in the middle of the parent.
        child: Column(
          // Column is also a layout widget. It takes a list of children and
          // arranges them vertically. By default, it sizes itself to fit its
          // children horizontally, and tries to be as tall as its parent.
          //
          // Invoke "debug painting" (press "p" in the console, choose the
          // "Toggle Debug Paint" action from the Flutter Inspector in Android
          // Studio, or the "Toggle Debug Paint" command in Visual Studio Code)
          // to see the wireframe for each widget.
          //
          // Column has various properties to control how it sizes itself and
          // how it positions its children. Here we use mainAxisAlignment to
          // center the children vertically; the main axis here is the vertical
          // axis because Columns are vertical (the cross axis would be
          // horizontal).

          mainAxisAlignment: MainAxisAlignment.center,
          children: <Widget>[

            Text(
              'Побед:',
            ),
            Text(
              '$_counterVin',
              style: Theme.of(context).textTheme.headline4,
            ),
            Text(
              'Поражений:',
            ),
            Text(
              '$_counterLose',
              style: Theme.of(context).textTheme.headline4,
            ),
            Text(
              '$message',
              style: Theme.of(context).textTheme.headline4,
            ),

            Row(
              textDirection: TextDirection.ltr,
              mainAxisAlignment: MainAxisAlignment.spaceEvenly,
              children: <Widget>[
                ElevatedButton(
                    child: Image.network('$leftBox', width: currentWidthLeft.toDouble(), height: currentHeidhtLeft.toDouble()),
                    onPressed:(){ generatePrize(0);}
                ),
                ElevatedButton(
                    child: Text(buttonText),
                    onPressed:(){ ContinueGame();}
                ),

                ElevatedButton(
                    child: Image.network('$rightBox', width: currentWidthRight.toDouble() ,height: currentHeidhtRight.toDouble()),
                    onPressed:(){ generatePrize(1);}
                ),

              ],
            ),

          ],
        ),

      ),


      // This trailing comma makes auto-formatting nicer for build methods.
    );
  }
}
