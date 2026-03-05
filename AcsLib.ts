import { NativeModules} from 'react-native';

const { AcsLibModule } = NativeModules;
if (!AcsLibModule) {
  throw new Error(
    'AcsLibModule not found. ' +
    'Make sure AcsLibPackage is registered in MainApplication.java ' +
    'and you have rebuilt the app.'
  );
}

const AcsLib = {
  format(
    lat: number,
    lon: number,
    coordType: string
  ): Promise<string> {
    return AcsLibModule.format(lat, lon, coordType);
  }
};

export default AcsLib;