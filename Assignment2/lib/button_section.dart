import 'package:flutter/material.dart';

import 'button_column.dart';

class ButtonSection extends StatelessWidget {
  const ButtonSection({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    Color color = Theme.of(context).primaryColor;
    return Row(
      mainAxisAlignment: MainAxisAlignment.spaceEvenly,
      children: [
        BuildButtonColumn(
            color: color, icon: const Icon(Icons.call), label: 'CALL'),
        BuildButtonColumn(
            color: color, icon: const Icon(Icons.near_me), label: 'ROUTE'),
        BuildButtonColumn(
            color: color, icon: const Icon(Icons.share), label: 'SHARE'),
      ],
    );
  }
}
