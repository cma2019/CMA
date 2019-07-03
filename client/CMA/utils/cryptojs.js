var CryptoJS = require('./aes.js'); 
var key = CryptoJS.enc.Utf8.parse("123456789abcdef1"); 
var iv = CryptoJS.enc.Utf8.parse("1fedcba987654321");
var username;
var selfkey;
//加密
function Decrypt(word) {
  var encryptedHexStr = CryptoJS.enc.Hex.parse(word);
  var srcs = CryptoJS.enc.Base64.stringify(encryptedHexStr);
  var decrypt = CryptoJS.AES.decrypt(srcs, key, {
    iv: iv,
    mode: CryptoJS.mode.CBC,
    padding: CryptoJS.pad.Pkcs7
  });
  var decryptedStr = decrypt.toString(CryptoJS.enc.Utf8);
  return decryptedStr.toString();
}
//解密
function Encrypt(word) {
  var srcs = CryptoJS.enc.Utf8.parse(word);
  var encrypted = CryptoJS.AES.encrypt(srcs, key, {
    iv: iv,
    mode: CryptoJS.mode.CBC,
    padding: CryptoJS.pad.Pkcs7
  });
  return encrypted.ciphertext.toString().toUpperCase();
}
function SetKey(codekey){
  /*
  console.log("setkey")
  console.log(codekey)
  console.log(this.key)
  this.key = CryptoJS.enc.Utf8.parse(codekey); 
  console.log(this.key)
  */
  console.log(key)
  //key = CryptoJS.enc.Utf8.parse("3333444422221111"); 
  key = CryptoJS.enc.Utf8.parse(codekey);
  console.log(key)
  return true
  //this.key = key;
}
//暴露接口
module.exports.Decrypt = Decrypt;
module.exports.Encrypt = Encrypt;
//module.exports.myDecrypt = myDecrypt;
//module.exports.myEncrypt = myEncrypt;
module.exports.SetKey = SetKey;
/*
module.exports = {
  username : username,
  usercode : usercode,
  key : key
}
*/

