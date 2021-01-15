class Ajax{
    static request = (method, url, callback, fallback, formData) => {
        let xhr = new XMLHttpRequest();
        xhr.open(method, url);
        xhr.onreadystatechange = () => {
            if (xhr.readyState === XMLHttpRequest.DONE) {
                if (xhr.status >= 200 && xhr.status < 300) {
                    callback(xhr.responseText);
                } else {
                    fallback();
                }
            }
        };
        if (typeof (formData) !== 'undefined') {
            xhr.send(formData);
        } else {
            xhr.send();
        }
    }
}
