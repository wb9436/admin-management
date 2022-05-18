function fmtPrice(price) {
    if (isNaN(price)) {
        return 0;
    }
    if (price < 1) {
        return 0;
    }
    var yuan = Math.floor(price / 100)
    var jiao = Math.floor((price - yuan * 100) / 10)
    var fen = price - yuan * 100 - jiao * 10
    return yuan + '.' + jiao + fen
}
