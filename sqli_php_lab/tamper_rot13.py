from lib.core.enums import PRIORITY
import codecs

__priority__ = PRIORITY.NORMAL

def tamper(payload, **kwargs):
    return codecs.encode(payload, 'rot_13')