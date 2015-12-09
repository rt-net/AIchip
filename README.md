# AIchip

本リポジトリはAIチップに関する資料およびサンプルプログラムを
まとめたものです.  各種ファイルの内容は以下になります.  

## circuit  
AIチップの回路図です.

## Processing  

### 概要

processingとはarudinoのようにC言語likeな文法で簡単なプログラミングができる言語です. <https://processing.org/>

グラフィック等の表示が比較的に簡単にできるのでちょっとしたプログラミングに適しています.  AIチップのいくつかのサンプルはprocessingを用いて記述されています.  processingの導入方法等についてはmanualフォルダ内のAI_CHIP_manual.pdfを参照してください.  


### ファイル内容

 * sample controller　Bluetooth経由でPCからAIチップの速度やLEDの点灯をコントロールするサンプル      
 * sample logger    　Bluetooth経由でPCからAIチップのデータをリアルタイムでグラフに表示するサンプル
 * sample App       　Bluetooth経由でPCからAIチップを動かす最小限のサンプル(矢印キーでモーターの速度変更)


## LPCXpresso  

### 概要

LPCXpressoはAIチップボード上の制御マイコン(lpc1343)の統合開発環境です.  
<http://www.nxp-lpc.com/lpc_boards/lpcxpresso/>
出荷時にAIチップには基本的な動作を実現するためのファームウェアが書き込まれています.  
このファームウェアのプロジェクトファイルが本フォルダに入っています.



### ファイル内容：
 * workspace




## Android 

### 概要

　Bluetooth経由でAndoroid端末からAIチップをコントロールするサンプルプログラムです.  
　このサンプルプログラムはAndroid Studio(統合開発環境)用です.

### アプリ機能
  
 * AIミニ四駆へ、複数のUIで命令が出せる  
 * モーター速度コントロール用シークバー  
 * モーターの前進、後進、停止ボタン  
 * LED点灯用ボタン  
 * LED、モーター、センサー制御用コマンド入力フォーム  
 * 音声認識によるモーター制御 （行け、動け、ゆっくり、止まれ等） 

## manual  

### 概要

各種マニュアルです.  お使いになられる前にマニュアルを熟読してください.

### ファイル内容

 * AI_CHIP_manual.pdf
   * 製品ついての基本的な説明とprosessing, bluetoothモジュールに関する記述です.      
 * AIチップ配線マニュアルver1.0.pdf  
   * AIチップ制御ボードと電池, モーターの配線方法について記述です.
 * AIミニ四駆ハッカソン.pdf  
   * 第一回AIミニ四駆ハッカソンの資料です.  AIチップに出荷時に書き込まれているファームウェアの通信プロトコルやAIチップのシステム構成に関して記述してあります.  
 * AIチップ_LPCXpresso環境構築方法.pdf  
   * AIチップ上のマイコンでプログラミングをする際に参照してください. 

## datasheet  

RN42(Bluetoothモジュール), mpu9150(9軸センサ), lpc1343(マイコン)のデータシートです.  


