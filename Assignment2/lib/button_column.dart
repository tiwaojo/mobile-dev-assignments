import 'dart:io';

import 'package:flutter/material.dart';
import 'package:flutter_share/flutter_share.dart';
import 'package:url_launcher/url_launcher.dart';

class BuildButtonColumn extends StatefulWidget {
  const BuildButtonColumn({
    super.key,
    required this.color,
    required this.icon,
    required this.label,
  });

  final Color color;
  final Icon icon;
  final String label;

  @override
  State<BuildButtonColumn> createState() => _BuildButtonColumnState();
}

// final Uri _url = Uri.parse('https://flutter.dev');
// Future<void> _launchUrl(Uri url) async {
//   if (!await launchUrl(url)) {
//     throw 'Could not launch $url';
//   }
// }

Future<void> share() async {
  await FlutterShare.share(
      title: 'SOFE4640 Assignment 2',
      text: 'Lorem ipsum dolor...',
      linkUrl: 'https://github.com/tiwaojo',
      chooserTitle: 'Tiwaojo's Github Account');
}

class _BuildButtonColumnState extends State<BuildButtonColumn> {
  @override
  Widget build(BuildContext context) {
    return Column(
      mainAxisSize: MainAxisSize.min,
      mainAxisAlignment: MainAxisAlignment.center,
      children: [
        IconButton(
          icon: widget.icon,
          color: widget.color,
          onPressed: () {
            setState(() {
              switch (widget.label) {
                case 'CALL':
                  if (Platform.isIOS) {
                    launchUrl(Uri.parse('tel:001-22-060-888'));
                  } else {
                    launchUrl(Uri.parse('tel:+1 222 060 888'));
                  }
                  break;
                case 'ROUTE':
                  if (Platform.isIOS) {
                    launchUrl(Uri.parse(
                        'https://www.google.com/maps/search/?api=1&query=52.32,4.917'));
                  } else {
                    launchUrl(Uri.parse(
                        'https://www.google.com/maps/search/?api=1&query=52.32,4.917'));
                  }
                  break;
                case 'SHARE':
                  share();
                  break;
              }
            });
          },
        ),
        Text(
          widget.label,
          style: TextStyle(
            fontSize: 12,
            fontWeight: FontWeight.w400,
            color: widget.color,
          ),
        ),
      ],
    );
  }
}
