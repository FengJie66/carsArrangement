import Cookies from 'js-cookie'

/*
* 设置cookies
* */
export function getCookies (key) {
  return Cookies.get(key)
}

/*
* 设置Cookies
* */

export function setCookies (key, value, expiresTime) {
  let minute = expiresTime
  let expires = new Date(new Date() * 1 + minute * 1000 * 60) //分钟
  return Cookies.set(key, value, { expires: expires })
}

/*
* 移除Cookies
* */

export function removeCookies (key) {
  return Cookies.remove(key)
}
