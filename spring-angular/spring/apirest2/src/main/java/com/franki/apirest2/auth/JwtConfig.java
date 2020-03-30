package com.franki.apirest2.auth;

public class JwtConfig {

	public static final String LLAVE_SECRETA="alguna.clave.secreta.12345678";
	
	public static final String RSA_PRIVADA = "-----BEGIN RSA PRIVATE KEY-----\n" + 
			"MIIEowIBAAKCAQEArMwo5sYzJZEiP0Hijhm/4UQv/oi0unLCdjUAOWRbdbtoxMYz\n" + 
			"9wh1EsSdykqJqggyxi2CrXW+C8uPePZ7LPfs9ozymku1Jadz81nsPyp4ye+dBGVA\n" + 
			"aTpuQ9/Kx2M02xIuNSQ3ne72VwlWZ/TbFDhhjgw5/mAc0gB8z2fnnPoN9yfSaWBC\n" + 
			"7b4G31Kj3fqzlP9fqYcvpNeMTKupxTZFp2f67FtPXVsOEAh6BOnCyYJM63YkQ+YO\n" + 
			"BdsUwZqA2pSyQ+TpmQns/5KI+dIoO5DjlO8/eRu1YCnsOzAb9Ar/2PBQoXC2E2si\n" + 
			"RPbBg8dhW+/EYGRiPsNQG7t/5IbldyUdYTTMqwIDAQABAoIBAFHBGkztUz+f5iMl\n" + 
			"qenvduA+obJ8vX1FVIoW029T8w3FyHDpEab8Ql/J1JnkIDPpQxSObbhh77Om8M+b\n" + 
			"VnXVKzEPSK0FysPw8SQe1G6iSpnIG3L78LiYo5jLZiZl4pkPhfswknvPlo9hZJc0\n" + 
			"uv5bGpTZZgrbMGePQX3jvCqCCF95mbnSlumxUXOe931k3f6GVGcG+V6iapn/2adl\n" + 
			"w5qoVuZXSyV+4/fwXIBIVzaYdEngQY9NkwXdfecY6PQ+Kr2k+PJsYbHT2vkZeD0P\n" + 
			"jvpZsxauS8KdDA96M+DeFF+H03DEOASCHVW1iLQgTGzdOdc7LsNqaXdh6SEAClI3\n" + 
			"1jk0GSECgYEA1zpAlGp6Ds48SGSdAH2H/wmoRvXqOY5Rf6qzWDlCqd2e+Eqcar05\n" + 
			"JsLRsoU5o5dxv5MWrECNo8rUQRjHwpIx8KyfhtOzz2V0idF6d9nDKz3B7GfxfZiS\n" + 
			"c3MKgqfoZlWula7QtGCVDizSTlqka0lm8CX/FO3qO6CVW9FVFzEzXzECgYEAzYg0\n" + 
			"nyEOXD1ewUlcjHuBB4/ZwPdJQQnxNupGd6CXdDkt4jVc1Mmr8ZtWKZ8+wUS44OaM\n" + 
			"cB3JyBQaG1qr3LK7xt8DWj1LWFrbjD8TD2tYfEozUNWakWwp/oIXkkXX2Q1WA2ya\n" + 
			"N0VCz4mTeR/pREwC2oIKNjGlLKDug2VH5G12SpsCgYAX42VLk83cWtOiDVjHtycj\n" + 
			"DYVZ6u2GWd991gp13lUh2XFNiap2xm3TeYgg0lylZafAZaj8kWWnKloIp6F0XRmh\n" + 
			"5ucF5fVtLYuTyGZZaxgYcpBVluvcztRicABYpB5NTXFFN9P+UUH6AsuM2s6pAcql\n" + 
			"GPKI9icrWxV4zXEuU82p4QKBgQCOl9zKOgjYfctrlhYbcOQH9wlAE1BDSz6KtubE\n" + 
			"wCnTfIgU1KjfCDu+l+KjZdQICkGp5smwTzpbEXc7WyC5MVeNldOhm7zWQ9Ll7iyv\n" + 
			"Ge597IzFnCxsj/pilKb92G70K4htP7+OcQL1MaGa+0S/GW0xY97p0m+iEtTnXDEQ\n" + 
			"jdDv2QKBgFElTFTfiClX5kEv2y2ItpZ2OgTW6MIWngR6rEVREtT/T+XKizoBy8az\n" + 
			"4wd1B0mozrc0RnAfH5UlgL+AqB0jLlm+4RZaTxvcmEfVqN4HMKvUrCfcdrbMLA7o\n" + 
			"voL/NuFc+8IGbbcmuPMiaiqfIsW5SB0LVGpAUnDfKM5js1L/e9PB\n" + 
			"-----END RSA PRIVATE KEY-----";
	
	
public static final String RSA_PUBLICA= "-----BEGIN PUBLIC KEY-----\n" + 
		"MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEArMwo5sYzJZEiP0Hijhm/\n" + 
		"4UQv/oi0unLCdjUAOWRbdbtoxMYz9wh1EsSdykqJqggyxi2CrXW+C8uPePZ7LPfs\n" + 
		"9ozymku1Jadz81nsPyp4ye+dBGVAaTpuQ9/Kx2M02xIuNSQ3ne72VwlWZ/TbFDhh\n" + 
		"jgw5/mAc0gB8z2fnnPoN9yfSaWBC7b4G31Kj3fqzlP9fqYcvpNeMTKupxTZFp2f6\n" + 
		"7FtPXVsOEAh6BOnCyYJM63YkQ+YOBdsUwZqA2pSyQ+TpmQns/5KI+dIoO5DjlO8/\n" + 
		"eRu1YCnsOzAb9Ar/2PBQoXC2E2siRPbBg8dhW+/EYGRiPsNQG7t/5IbldyUdYTTM\n" + 
		"qwIDAQAB\n" + 
		"-----END PUBLIC KEY-----";
}
