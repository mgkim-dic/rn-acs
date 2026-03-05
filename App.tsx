import React, { useState } from 'react';
import { View, Text, Button } from 'react-native';
import AcsLib from './AcsLib';

export default function App() {
  const [result, setResult] = useState<string>("");

  const handleConvert = async () => {
    try {
      const coord = await AcsLib.format(
        37.5000000, // 위도
        127.5000000, // 경도
        "UTM"
      );
      setResult(coord);
    } catch (e) {
      console.error(e);
      setResult("변환 실패");
    }
  };

  return (
    <View style={{ flex: 1, justifyContent: 'center', alignItems: 'center' }}>
      <Text>AcsLib Test</Text>

      <Button
        title="37.5000000,127.5000000 위도,경도 → UTM 변환"
        onPress={handleConvert}
      />

      <Text style={{ marginTop: 20 }}>
        {result}
      </Text>
    </View>
  );
}