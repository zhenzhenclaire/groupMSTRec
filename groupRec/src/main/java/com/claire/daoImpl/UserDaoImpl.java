package com.claire.daoImpl;

import com.claire.dao.UserDao;
import com.claire.entity.User;

/**
 * Created by admin on 2016/4/5.
 */
public class UserDaoImpl implements UserDao{

	@Override
	public void makeUserReflectionTable() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getReflectedId(String uid) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getOriginalId(int reflectedUid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User findUserByUid(String uid) {
		// TODO Auto-generated method stub
		/*
	     * line 1: {"yelping_since": "2004-10", "votes": {"funny": 167, "useful": 280, "cool": 245}, "review_count": 108, "name": "Russel", "user_id": "18kPq7GPye-YQ3LyKyAZPw", "friends": ["rpOyqD_893cqmDAtJLbdog", "4U9kSBLuBDU391x6bxU-YA", "fHtTaujcyKvXglE33Z5yIw", "8J4IIYcqBlFch8T90N923A", "wy6l_zUo7SN0qrvNRWgySw", "HDQixQ-WZEV0LVPJlIGQeQ", "T4kuUr_iJiywOPdyM7gTHQ", "z_5D4XEIlGAPjG3Os9ix5A", "i63u3SdbrLsP4FxiSKP0Zw", "pnrGw4ciBXJ6U5QB2m0F5g", "ytjCBxosVSqCOQ62c4KAxg", "r5uiIxwJ-I-oHBkNY2Ha3Q", "niWoSKswEbooJC_M7HMbGw", "kwoxiKMyoYjB1wTCYAjYRg", "9A8OuP6XwLwnNb9ov3_Ncw", "27MmRg8LfbZXNEHkEnKSdA", "Bn4sJUTtKFZQt0FKHF2Adw", "uguXfIEpI65jSCH5MgUDgA", "6VZNGc2h2Bn-uyuEXgOt5g", "AZ8CTtwr-4sGM2kZqF6qig", "S742m-AuQicMSLDdErrLZQ", "uGmQ6ab4iVpWn5m61VFhkQ", "GJYJX4SujVj3BR8v2F9PDQ", "3shjifK-vZkIHciyy_KbYA", "4lc_H2Cf7CO0tCgyA3aSVQ", "Tunkp_F1R_uFBJQTsDxD4g", "B9pKfr27czBbCoAIircZdQ", "pePGMO6EbDpbaZ7D2m6HIg", "XRM8W6HUoXbrYKR3BCj9Rg", "8DqIWXsKXOipfduYEfFpNw", "dvRVX54Z9f7Om51NsTRX1w", "CM0saLQmk4oAB17UmQTV-g", "HANb8-8InWnju-XzBQQSBw", "JuJeZeQJgv7bUreY7a1SlQ", "2NVVEEYhhoVELdaPILFrDQ", "e4M9_S-ASmRys3DvKQfotw", "XA109o963exKgoVGcg9z7w", "tVAKdax3Jbf24R7OJB99JQ", "OMWT-Z6OnJLcg44lCuDuhw", "d82F_FFtowYtjGtxRySehA", "pW91HUnVz6ssLZ4dY-ztyQ", "ojQYtstkGXtcryk5I9GTYA", "4hxVql33ldY_gkf3rG8_3w", "0arQ82n4mvrc42U8KuoE6g", "w6Vv-kldGpmvSGqXvTbAdQ", "NZeCINmoOJ8vsQvYkZAp9w", "n9ltC5DxMjefffeMfBgcXQ", "QBj2AL66bEAmK3ULkrn1Gw", "ayZlWyV1M2k_UWx1dreIDg", "AG1KRDkIa8QCCPsMnINEOg", "P-lfV8cdAgEOZJkw2dJpsw", "7Pef9EA21szXXOPJo6Gb5w", "YvtRBqCqhSIhGGREqcqnQw", "8M7I2-s5nQ8LSkuCvN0Xqg", "-ETxy7f37BBQXhw5zJfkrQ", "TjPt2hD56jfpmtoa6kVVRg", "uZgFEPEUIWJMaaqvMlxYoA", "foIpFZp5EwvUDSnyMKKAnQ", "YKppbE7ogNmbtVrluxEF4A", "D7uXj1_0pE_Fa2YeLkOkyw", "eVV99D3EZvkuswIvIySKiw", "PhwgVPqpJlxu40AxAOUBbg", "gWcz4QFTW76q8VtpIlZEHQ", "wpcoiQNbO5x0xkZwKFVUFw", "Ot-QjYpIdVsluuxcqidjkA", "E3C_gEhktux9Ca-Z2fmNTw", "ClaGLOr72DPJlQhRc9HHyQ", "sc6wS6YOFIgtN2XuJI8_WQ", "dD5mA6kwVlBHvrNtX9EyZQ", "qwvgycPC5u4JZv6DfawPqw", "As_oVE27fiE-0CbDJwUDiA", "SuzSrMWoycf4DXL_DMMdNg", "y0SRKlzVuvj-mbQtbsWx-A", "qbfQRHLvZk5WSkKY0l_lMw", "D6A0rn5MIg7AVRrVeouABg", "KUqpJ9eKREuvlnlitJJRYg", "gOvKWYXobX_Pgzsz-K8auA", "xzdZTsR2KKcQfjF4OjJeOw", "DyfA7aPzRQ81kJnbkuIiJQ", "tA2suyZG_qWIuNB_HR-YRA", "R5QmtpSsrQ9mqIxjZIv3uw", "5T8SdBwiH0jdwatUJ7KFUg", "1Gfn4nlqPEIvRX4cnctJlg", "A4_fxqSj4tgYet8OE3jTyA", "oPD_t6BVpfhvu1-TS4FNzA", "gn3Y0mWduWrvT1hvu7fv4A", "NUeLSLukiPPCSK6EVdUo_Q", "rtrh46NJfksxR4TgljYg9w", "5zpVMWD-q6nEYapiWrSyXw", "ooZ7TV6IuxuqbFgnqaoSsQ", "8phGuOZ0NIfeJiTX-ZDZLA", "8L1p_zlmAB_uWyaW0QTaCg", "s7ZSag-bHo0C4fSBDJeBDw", "5ANDwUyQqsRhEl7QBKRFiw", "KMGF6KAnPRoOTFVXBy9jtQ", "aLz2ljnMweZUlvP-q4TayQ", "6uM5lx0DCX4ctQlebtxraw", "Hc6agWZ9jGCxsPAqQwlf2w", "z39ulrgtvEMB2oeokx0-mw", "wLt28qh-46LFhKOeByWZ0w", "p4Jg_rO_AsCWZGMd_w8xjw", "pJppmQyW74ejj8xCa2SHuA", "hzaqnSxfRYv_Wbp6CMKtQw", "0QwV8PJk9PJDFaMNw1Fcsw", "6pC5j-hfP4xLZB2W0Wi1vg", "8nAj60xsBjcHXLLSs7cPqw", "NhbV8AbaQiKNzEU11cbxcQ", "5Szwd1CSepRXwAFH12nPzQ", "ifD7o8Mi67yNYwgWL5CbKg", "AaZdXn0I6F5bdIVwGpxdDA", "YwcacxA7WCB9fryDHDxF8w", "HBFtzjRarXxDH8xZRSNsPA", "zi0kxtr5gq0Flg7jYdIlMA", "oWEdmTYl13VHh1A_Mkhh2g", "n1GSQ7VT9sTL5MlCWolbNw", "PO8XNxGUizzzKGHmn24fQQ", "JTRxzA1AZ8Zfwj7FpH2CnA", "AixLPxra_4HirfchjPkiWA", "xOK3i0wgmkpGu54BuIcwQA", "F9T6m1YdRFreyKDufcyoOQ", "-cscgA-lVPipPwEX4RmcgQ", "ACLuEsNHV7lfq1O7CHuFaA", "UyOtYjnavsr-Dtjo6Xy91g", "J11gYVC1u9S5jqEg3oh-gQ", "9LZP7Ll7N9zkuWfA5DUAYw", "SbM1m5nejHDD1pJePsar9g", "wF7_-zf7R8Rri7Kq-a0tlw", "B5o7JC4iRJ7bKPRnoYEIHg", "SAPz30NHFJ95SBlvz5cTqw", "fzkO1lGkx8MHhT8S4Rbg3w", "tlSSQwfHYJany7wPoTH46A", "2VfhMrTJ9bZ0O4PZmRmxKA", "odeoZZHRQp6TkeoEQk3eeA", "2XCsVpAMsGUykrM-BBFEXA", "qVePixO2kuK0Dr03upCWFg", "D9CIW04wrcQzZNdCEoASAg", "G-iuXcbJnX3sA3lhB6nFhQ", "3utEqyNzFcxH8E8pm7ByPA", "T5TZfn9_9X-LTbRtaMAgUw", "fczQCSmaWF78toLEmb0Zsw", "Hyhmpb3UnbjtSEXvgviCow", "FI-Fjxr3fUBx2q7icnqcfA", "S8ZY1HEDoECqKrIUY34JOg", "3JtdBvnY6Wzp22gt5zFwDg", "470dZswdTHz5aaGPvIYNpg", "JT5kM6wRwh7cNt8IAuX_sw", "r7V9Nhes-Eg-lH0cqfpFFw", "2m3lE8p8T2nFRDxIUaRXew", "bZFRqP7s0Vszxeu8_IwYow", "snd45oedwXxF9tsHUX_6dg", "8jEK8gxHYg9w6sINujO0MQ", "LTWY2Ee-SYu8ItVOebD3CQ", "7zpDhrRZRTGCkAh3SHbEww", "NSg7k2UFt_HqM2mvsw-n0w", "egdj4-wXAzYzT5bmTAHYqQ", "wxhf-RSExCGECZuz_iVV7w", "C6IOtaaYdLIT5fWd7ZYIuA", "jntSRFnN_O7QfFzZo24rvA", "yfQwWLYtqwHKiky1hiTBew", "WZgoM8UuzxjZf1AaWx3qZQ", "cNj8rheuldPDX9zF6q2yaA", "OqOCMRpVNvvdPDMhoG_DCA", "9ZonzxW0DAqIUO_5tDqZig", "rLQNlHnIKdtoqoU77CbEUQ", "LbgQK5B_5IkN77FgRJHhrg", "it23EnutJ6fOjeYQBDKJug", "1XWzXgFvcu5pU2wj1ONQ_Q", "92j9xiUMOsM3HviIGoR8Yg", "AP6udBIStvWLY0T5rS_24A", "G_PCzMHcOLoMyyEsxW1DFQ", "ZuQ87LQYWRw3AHj-jEiQ1Q", "zdutsCEMdvc0B8s3_XN3nQ", "fRbbPPUdMCmL9o39yshi2g", "pjgE1d3csesAkgn6rQI_RQ", "C7nUczj6n0ouxWqp1I7cNQ", "D8EE4gbAiXS3DF_HNRJfAw", "Milv8k4zZsoCaNFZugKtJw", "GALRNo-AbcAHxEdmqKl--w", "XOe-I25-W_FnbYfQyafylw", "kuP0AMnSGgkLfNSt0wOAgA", "EwMgdjuPXLAuxxu3IOUyKA", "Mx-vxv_V-SQCe76w4RmUfA", "6xxVdJBdMREj1fCqjm6UtA", "m61SjiXAIlOY1qAXOHEk4Q", "R4sYowLqQQEpxWjQbqaFvg", "OCkge7lzOw33bqMs1244Yw", "rjJJ8QQg-eqb7mgMxArgBA", "T_wjLgPOPXry7Bea4MzoVQ", "hBwbBaBqofCgvkJ3PatuVw", "epTMlecM8SZNjDhBsd64Jg", "PUMROYfbVgKypcfJNxs1mw", "82ZqgHOfUMV1xtpG2KfisA", "IN2HcBun5PpyfQK8OC68iw", "yQZL8MTulNh3d3de-N0oug", "nZFcWvgPU8T4JCjOVvJU8Q", "jIuc3nM9R0KWX3v-8OyLsQ", "F1oGgK3NtTYnyRMxupiiuA", "nGYaT5sAuqKkd48dqX_2Kw", "NzP9OoNP8vBMRUSs0UXBNw", "ZTs-go-xYRQOp3PSw_XGDg", "gc4rNAgbGydNMAPJ85FZag", "KYuljD4Tovti3J1pdBnT4g"], "fans": 70, "average_stars": 4.14, "type": "user", "compliments": {"profile": 8, "cute": 15, "funny": 11, "plain": 25, "writer": 9, "note": 20, "photos": 14, "hot": 48, "cool": 78, "more": 3}, "elite": [2005, 2006]}
	     */

		return null;
	}

    
}
