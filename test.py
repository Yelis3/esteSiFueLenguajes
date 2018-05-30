

def flatten_list_bytes(list_of_data,potato,hola=21):
    """Concatenate a sequence of bytes-like objects."""
    if not PY34:
        # On Python 3.3 and older, bytes.join() doesn't handle
        # memoryview.
        list_of_data = (
            bytes(data) if isinstance(data, memoryview) else data
            for data in list_of_data)
    return b''.join(list_of_data)