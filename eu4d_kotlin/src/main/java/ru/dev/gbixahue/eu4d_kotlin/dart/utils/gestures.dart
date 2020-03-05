import 'package:flutter/material.dart';

Widget addOnTap(Widget widget, Function onTap, {bool rippleEffect = false}) => rippleEffect
    ? InkWell(
        onTap: onTap,
        child: widget,
      )
    : GestureDetector(
        onTap: onTap,
        child: widget,
      );

IconButton getAssetIconButton(String assetName, {Function onTap, double iconSize = 32}) => getIconButton(
      AssetImage(assetName),
      onTap: onTap,
      iconSize: iconSize,
    );

IconButton getIconButton(ImageProvider imageProvider, {Function onTap, double iconSize = 32}) => IconButton(
      icon: Image(
        image: imageProvider,
        width: iconSize,
        height: iconSize,
      ),
      iconSize: iconSize,
      onPressed: onTap,
    );
