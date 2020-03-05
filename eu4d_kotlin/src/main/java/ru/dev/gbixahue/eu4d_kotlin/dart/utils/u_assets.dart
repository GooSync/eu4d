class Asset {
  static const _R = "&";

  static const BG_SPLASH = "assets/bg_splash.jpg";
  static const BG_WELCOME = "assets/bg_welcome.png";
  static const BG_GAME_MAIN = "assets/bg_game_main.jpg";
  static const BG_ACTION = "assets/bg_action.png";
  static const BG_LIGHTING_RAYS = "assets/bg_lighting_rays.png";
  static const BG_SHOP = "assets/bg_shop.png";
  static const BG_SHOP_GI = "assets/bg_shop_gi.png";
  static const BG_SHOP_PI_PRICE = "assets/bg_shop_gi_price.png";
  static const BG_DECK_DISCOUNT = "assets/bg_deck_discount.png";
  static const BG_DECK_BORDER = "assets/bg_deck_border.png";
  static const BG_CARD_BORDER = "assets/bg_card_border.png";
  static const BG_CARD_TL = "assets/bg_card_tl.png";
  static const BG_CARD_TR = "assets/bg_card_tr.png";
  static const BG_CARD_BL = "assets/bg_card_bl.png";
  static const BG_CARD_BR = "assets/bg_card_br.png";
  static const BG_STUFF_ITEM = "assets/bg_stuff_item.png";
  static const BG_HEART_FLOWER = "assets/bg_heart_flower.png";
  static const BG_HALLOWEEN_DECK = "assets/bg_halloween_deck.png";
//  static const BG_HALLOWEEN_MAIN = "assets/bg_halloween_main_screen.jpg";
//  static const BG_HALLOWEEN_CARD_GAME = "assets/bg_halloween_card_screen.jpg";
  static const BG_DELIMITER = "assets/bg_delimiter.png";

  static const IC_WELCOME_QUESTIONS = "assets/icons/ic_welcome_questions.png";
  static const IC_WELCOME_MASK = "assets/icons/ic_welcome_mask.png";
  static const IC_MASK = "assets/icons/ic_mask.png";
  static const IC_LOGO = "assets/icons/ic_logo.png";
  static const IC_CHILE = "assets/icons/ic_chile.png";
  static const IC_SHARE = "assets/icons/ic_share.png";
  static const IC_REFRESH = "assets/icons/ic_refresh.png";
  static const IC_EMAIL = "assets/icons/ic_email.png";
  static const IC_GIFT = "assets/icons/ic_gift.png";
  static const IC_BACK = "assets/icons/ic_back.png";
  static const IC_NEXT = "assets/icons/ic_arrow_next.png";
  static const IC_PROMO_CARD = "assets/icons/ic_promo_code.png";

  static const IC_LOCK = "assets/icons/ic_lock_closed.png";
  static const IC_LOCK_UNLOCKED = "assets/icons/ic_lock_opened.png";
  static const IC_PRICE_TAG = "assets/icons/ic_price_tag.png";
  static const IC_HEART_BIG = "assets/icons/ic_heart_big.png";
  static const IC_CALENDAR_GREY = "assets/icons/ic_calendar_grey.png";
  static const IC_CALENDAR_GREEN = "assets/icons/ic_calendar_green.png";
  static const IC_CALENDAR_RED = "assets/icons/ic_calendar_red.png";
  static const IC_CHECK_IN = "assets/icons/ic_check_in.png";
  static const IC_SHOP_HEART_INDEX_TEMPLATE = "assets/icons/ic_shop_heart_$_R.png";
  static const IC_STUFF_NAME_TEMPLATE = "assets/icons/attr/ic_attr_$_R.png";

  static indexed(int index, String template) => named(index.toString(), template);

  static named(String name, String template) => template.replaceAll(_R, name);
}
